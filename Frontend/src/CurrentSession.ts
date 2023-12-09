export const getJwtToken = () => {
  return window.sessionStorage.getItem("jwtToken");
};

export const setJwtToken = (jwtToken: string) => {
  window.sessionStorage.setItem("jwtToken", jwtToken);
};

export const clearCurrentSession = () => {
  window.sessionStorage.clear();
};
