import axios, { AxiosInstance } from 'axios';

const baseURL = import.meta.env.VITE_API_URL;

interface ServiceEndpoints {
  [key: string]: number;
}

const CreateServiceApi = (service: string): AxiosInstance => {
  const portMap: ServiceEndpoints = {
    review: 8082,
    member: 8081,
    notifictation: 8084,
    // 다른 서비스들의 포트 번호 추가
  };

  const servicePort = portMap[service];
  const serviceEndpoint = `${baseURL}:${servicePort}/api/v1`;
  const api = axios.create({
    baseURL: serviceEndpoint,
  });

  // Interceptor를 사용하여 accessToken을 요청에 추가
  // const { authToken } = useAuthStore();
  const accessToken = localStorage.getItem('accessToken');

  api.interceptors.request.use((config) => {
    config.headers.Authorization = accessToken;
    return config;
  });
  return api;
};

interface ClientAPI {
  [key: string]: AxiosInstance;
}

const clientApi: ClientAPI = {
  review: CreateServiceApi('review'),
  member: CreateServiceApi('member'),
  notification: CreateServiceApi('notification'),
};

export default clientApi;
