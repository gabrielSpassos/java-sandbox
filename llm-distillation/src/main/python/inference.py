from transformers import AutoTokenizer, AutoModelForCausalLM
import torch

model_path = "./student-model"

tokenizer = AutoTokenizer.from_pretrained(model_path)
model = AutoModelForCausalLM.from_pretrained(model_path)

prompt = "### Instruction:\nExplain Kubernetes\n\n### Response:\n"

inputs = tokenizer(prompt, return_tensors="pt")

outputs = model.generate(
    **inputs,
    max_new_tokens=200,
    temperature=0.7
)

print(tokenizer.decode(outputs[0], skip_special_tokens=True))