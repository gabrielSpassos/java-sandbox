import base64
import gzip
import json
import sys


def main():
    payload = json.loads(sys.stdin.readline())

    response = payload["response"]

    if response.get("status") != 200:
        print(json.dumps(payload))
        return

    body = response.get("body", "")

    if response.get("encodedBody"):

        # Hoverfly encodedBody=true means the body is base64 encoded.
        decoded = base64.b64decode(body)

        # The HTTP response is gzip encoded.
        if response.get("headers", {}).get("Content-Encoding") == ["gzip"]:
            decoded = gzip.decompress(decoded)

        body = decoded.decode("utf-8")

    # Now we finally have JSON.
    json_body = json.loads(body)

    # Modify ONLY the field you want.
    json_body["usd"]["brl"] = 10.153475

    # Serialize it back to JSON.
    body = json.dumps(json_body, separators=(",", ":")).encode("utf-8")

    # Compress again because the original response was gzip encoded.
    if response.get("headers", {}).get("Content-Encoding") == ["gzip"]:
        body = gzip.compress(body)

    # Hoverfly expects encodedBody=true to remain base64 encoded.
    if response.get("encodedBody"):
        response["body"] = base64.b64encode(body).decode("ascii")
    else:
        response["body"] = body.decode("utf-8")

    # Update Content-Length.
    response["headers"]["Content-Length"] = [
        str(len(response["body"]))
    ]

    print(json.dumps(payload))


if __name__ == "__main__":
    main()