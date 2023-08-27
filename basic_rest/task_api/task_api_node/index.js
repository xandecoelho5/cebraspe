const http = require("http");

const server = http.createServer((req, res) => {
  if (req.method === "GET") {
    res.writeHead(200, { "Content-Type": "application/json" });
    const message = JSON.stringify({ message: "Hello, World!" });
    res.end(message);
  } else if (req.method === "POST") {
    let body = "";
    req.on("data", (chunk) => {
      body += chunk.toString();
    });

    req.on("end", () => {
      const data = JSON.parse(body);
      const response = JSON.stringify({ received_data: data });
      res.writeHead(200, { "Content-Type": "application/json" });
      res.end(response);
    });
  }
});

const PORT = 8000;
server.listen(PORT, () => {
  console.log(`Server running at http://localhost:${PORT}`);
});
