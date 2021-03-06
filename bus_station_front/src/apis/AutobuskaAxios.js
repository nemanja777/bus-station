import axios from 'axios';
import { logout } from '../services/auth';

var AutobuskaAxios = axios.create({
  baseURL: 'http://localhost:8080/api',
  /* other custom settings */
});

AutobuskaAxios.interceptors.request.use(
  function success(config) {
    const token = window.localStorage['jwt'];
    if (token) {
      config.headers['Authorization'] = 'Bearer ' + token;
    }
    return config;
  }
);

AutobuskaAxios.interceptors.response.use(
  function uspeh(odgovor) {
    return odgovor;
  },
  function neuspeh(greska) {
    let token = window.localStorage.getItem('jwt');
    if (token) {
      if (greska.response && greska.response.status == 403) {
        logout();
      }
    }

    throw greska;
  }
);



export default AutobuskaAxios;
