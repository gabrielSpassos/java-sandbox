MODEL_NAME = "mistralai/Mistral-7B-v0.1"   # teacher-like base
OUTPUT_DIR = "./student-model"

MAX_LENGTH = 512

# LoRA params
LORA_R = 16
LORA_ALPHA = 32
LORA_DROPOUT = 0.05

# Training
BATCH_SIZE = 2
GRAD_ACCUM = 4
LR = 2e-4
EPOCHS = 2
