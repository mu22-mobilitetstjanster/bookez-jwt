/**
 * Store and retrieve data from localStorage & sessionStorage
 */

export default {
  storeLocal(ref: string, item: any) { // remains after closed browser
    localStorage.setItem(ref, JSON.stringify(item));
  },
  storeSession(ref: string, item: any) { // resets on closed browser
    sessionStorage.setItem(ref, JSON.stringify(item));
  },
  fetchLocal<Type>(ref: string): Type {
    const item = localStorage.getItem(ref) || '{}';
    return JSON.parse(item);
  },
  fetchSession<Type>(ref: string): Type {
    const item = sessionStorage.getItem(ref) || '{}';
    return JSON.parse(item);
  }
}