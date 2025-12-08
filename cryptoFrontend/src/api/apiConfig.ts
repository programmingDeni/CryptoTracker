import { Configuration } from "./configuration";

export const apiConfig = new Configuration({
  basePath: "http://localhost:8080",
  accessToken: () => localStorage.getItem("auth_token") || "",
});
