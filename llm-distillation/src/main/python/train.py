import torch
from datasets import load_dataset
from transformers import (
    AutoTokenizer,
    AutoModelForCausalLM,
    TrainingArguments,
    Trainer,
    DataCollatorForLanguageModeling
)
from peft import LoraConfig, get_peft_model
import config


# ---------------------------
# 1. Load dataset
# ---------------------------
dataset = load_dataset("json", data_files="dataset.jsonl")

# ---------------------------
# 2. Format dataset
# ---------------------------
def format_example(example):
    prompt = f"### Instruction:\n{example['prompt']}\n\n### Response:\n{example['completion']}"
    return {"text": prompt}

dataset = dataset.map(format_example)

# ---------------------------
# 3. Tokenizer
# ---------------------------
tokenizer = AutoTokenizer.from_pretrained(config.MODEL_NAME)

if tokenizer.pad_token is None:
    tokenizer.pad_token = tokenizer.eos_token


def tokenize(example):
    return tokenizer(
        example["text"],
        truncation=True,
        padding="max_length",
        max_length=config.MAX_LENGTH
    )

tokenized_dataset = dataset.map(tokenize, batched=True, remove_columns=dataset["train"].column_names)

# ---------------------------
# 4. Load model (4-bit optional)
# ---------------------------
model = AutoModelForCausalLM.from_pretrained(
    config.MODEL_NAME,
    device_map="auto",
    torch_dtype=torch.float16
)

# ---------------------------
# 5. Apply LoRA
# ---------------------------
lora_config = LoraConfig(
    r=config.LORA_R,
    lora_alpha=config.LORA_ALPHA,
    target_modules=["q_proj", "v_proj"],
    lora_dropout=config.LORA_DROPOUT,
    bias="none",
    task_type="CAUSAL_LM"
)

model = get_peft_model(model, lora_config)

model.print_trainable_parameters()

# ---------------------------
# 6. Training setup
# ---------------------------
training_args = TrainingArguments(
    output_dir=config.OUTPUT_DIR,
    per_device_train_batch_size=config.BATCH_SIZE,
    gradient_accumulation_steps=config.GRAD_ACCUM,
    learning_rate=config.LR,
    num_train_epochs=config.EPOCHS,
    logging_steps=10,
    save_steps=100,
    fp16=True,
    report_to="none"
)

data_collator = DataCollatorForLanguageModeling(
    tokenizer=tokenizer,
    mlm=False
)

trainer = Trainer(
    model=model,
    train_dataset=tokenized_dataset["train"],
    args=training_args,
    data_collator=data_collator
)

# ---------------------------
# 7. Train
# ---------------------------
trainer.train()

# ---------------------------
# 8. Save model
# ---------------------------
trainer.save_model(config.OUTPUT_DIR)
tokenizer.save_pretrained(config.OUTPUT_DIR)

print("✅ Training complete. Model saved to:", config.OUTPUT_DIR)