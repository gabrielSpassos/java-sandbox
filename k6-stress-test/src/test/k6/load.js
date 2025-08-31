import http from "k6/http";
import { check, sleep } from "k6";

export const options = {
  vus: 5,           // 10 virtual users
  duration: "20s",   // run for 30 seconds
};

export default function () {
  // Calculate dungeon
  let id = uuidv4();

  let calculateRes = http.post(
    "http://dungeon-service:8080/v1/dungeons",
    JSON.stringify({
      id: id,
      dungeon: [
        [-2, -3, 3],
        [-5, -10, 1],
        [10, 30, -5],
      ],
    }),
    { headers: { "Content-Type": "application/json" } }
  );

  check(calculateRes, {
    "dungeon calculated": (r) => r.status === 200,
  });

  sleep(1);
}

function uuidv4() {
  return 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, c => {
    const r = Math.random() * 16 | 0;
    const v = c === 'x' ? r : (r & 0x3 | 0x8);
    return v.toString(16);
  });
}