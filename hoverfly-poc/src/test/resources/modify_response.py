import json
import sys


def main():
    payload = json.loads(sys.stdin.readline())

    response = payload.get("response")

    if response and response.get("status") == 200:
        body = json.loads(response["body"])

        usd = body["usd"]
        usd["brl"] = 10.1534

        response["body"] = json.dumps(body)

        response["headers"]["Content-Length"] = [
            str(len(response["body"].encode("utf-8")))
        ]

    print(json.dumps(payload))


if __name__ == "__main__":
    main()