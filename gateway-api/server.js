import express from "express";

const app = express();
const PORT = process.env.PORT || 8080;

app.get("/health", (_req, res) => res.status(200).json({status: "UP"}));

// Placeholder routing. In production, proxy/route to microservices by service name on the same Docker network.
app.get("/", (_req, res) => res.send("Gateway API is running. Add routes / proxy here."));

app.listen(PORT, () => console.log(`[gateway] listening on port ${PORT}`));
