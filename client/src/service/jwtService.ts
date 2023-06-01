/**
 * Decoding and reading from jwt stored tokens
 * 
 * Uses cacheService for fetching and storing token information
 */

import cacheService from "./cacheService";

export default {
  storeJwt(token: string) {
    cacheService.storeLocal("JWT_TOKEN", token);
  },
  getJwt(): string {
    return cacheService.fetchLocal("JWT_TOKEN");
  },
  clearJwt() {
    cacheService.storeLocal("JWT_TOKEN", '');
  },
  getProp(propName: string): string {
    const token = this.getJwt();
    if(Object.keys(token).length === 0) return '';

    const parts = token.split('.');
    const payload = JSON.parse(atob(parts[1]));

    return payload[propName] || '';
  }
}