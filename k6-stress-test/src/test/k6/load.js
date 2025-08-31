import http from "k6/http";
import { check, sleep } from "k6";

export const options = {
  stages: [
    { duration: "10s", target: 5 },   // ramp up to 5 VUs
    { duration: "20s", target: 10 },  // ramp up to 10 VUs
    { duration: "20s", target: 0 },   // ramp down
  ],
  thresholds: {
    http_req_duration: ["p(95)<500"],  // 95% of requests < 500ms
    "checks": ["rate>0.99"],           // 99% of checks pass
  },
};

export default function () {
  // Calculate dungeon
  let calculateRes = http.post(
    "http://dungeon-service:8080/v1/dungeons",
    JSON.stringify({
      id: uuidv4(),
      dungeon: generateDungeon(),
    }),
    { headers: { "Content-Type": "application/json" } }
  );

  check(calculateRes, {
    "status is 200": (r) => r.status === 200,
    "response time < 500ms": (r) => r.timings.duration < 500,
  });

  sleep(Math.random() * 2 + 0.5);  // sleep 0.5s–2.5s

  const id = calculateRes.json("id");

  // Second request: get dungeon by id
  let getRes = http.get(`http://dungeon-service:8080/v1/dungeons/${id}`);

  check(getRes, {
    "status is 200": (r) => r.status === 200,
    "response time < 500ms": (r) => r.timings.duration < 500,
  });

  sleep(Math.random() * 2 + 0.5);  // sleep 0.5s–2.5s

  // Thrid request: get dungeon by id not found
  let getResNotFound = http.get(`http://dungeon-service:8080/v1/dungeons/${uuidv4()}`);

  check(getResNotFound, {
    "status is 404": (r) => r.status === 404,
    "response time < 500ms": (r) => r.timings.duration < 500,
  });

  sleep(Math.random() * 2 + 0.5);  // sleep 0.5s–2.5s
}

function uuidv4() {
  return 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, c => {
    //random number between 0 and 15.999 
    //| 0 → bitwise OR 0, truncates to an integer 0–15 (hex digit).
    const r = Math.random() * 16 | 0;
    //r & 0x3   → keeps only the last 2 bits (0–3)
    //| 0x8     → sets the first 2 bits to 10 (8–11 in hex)
    const v = c === 'x' ? r : (r & 0x3 | 0x8);
    return v.toString(16);
  });
}

function generateDungeon(rows = 3, cols = 3) {
  return Array.from({ length: rows }, () =>
    Array.from({ length: cols }, () => Math.floor(Math.random() * 20) - 10)
  );
}