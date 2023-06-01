/**
 * Fetch information about users
 * 
 * If the information is about other users, it'll require admin access
 */

import tokenAxios from "@/interceptor/tokenAxios";
import type { UserDetails } from "@/model/UserDetails";

const BASE_USER_ULR = "http://127.0.0.1:8080/users"

export default {
  async fetchAll(): Promise<Array<UserDetails>> {
    const resp = await tokenAxios.get(BASE_USER_ULR + "/");
    return resp.data;
  }
}