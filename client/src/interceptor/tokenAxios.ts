import jwtService from "@/service/jwtService";
import axios, { type InternalAxiosRequestConfig } from "axios";

const tokenAxios = axios.create();

tokenAxios.interceptors.request.use((config: InternalAxiosRequestConfig) => {
  config.headers.Authorization = 'Bearer ' + jwtService.getJwt();
  return config;
});

export default tokenAxios;