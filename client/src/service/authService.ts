/**
 * Register and login
 * 
 * Uses jwtService for storing JWT information 
 */

import type { SigninAuthDetails, SignupAuthDetails } from "@/model/AuthDetails";
import axios from "axios";
import jwtService from "./jwtService";

const BASE_AUTH_URL = 'http://127.0.0.1:8080/auth';

export default {
  async register(authDetails: SignupAuthDetails) {
    const resp = await axios.post(BASE_AUTH_URL + "/register", authDetails);
    jwtService.storeJwt(resp.data);
  },
  async login(authDetails: SigninAuthDetails) {
    const resp = await axios.post(BASE_AUTH_URL + "/login", authDetails);
    jwtService.storeJwt(resp.data);
  }
}