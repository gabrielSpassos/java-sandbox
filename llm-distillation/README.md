# LLM Distillation

## Design

```
Spring AI service
      │
      ├─ Generate teacher responses
      │
      ├─ Store dataset (prompt + answer)
      │
      ├─ Export JSONL dataset
      │
      └─ Train student model (outside Spring)
```

## Usage

```shell
export OPENAI_API_KEY=my-secret
```

## Output

```
```
