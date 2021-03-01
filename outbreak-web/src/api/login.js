import request from '@/request'

export function login(username, password) {
  const data = {
    username,
    password
  }
  return request({
    url: '/user/login',
    method: 'post',
    data
  })
}

export function logout() {
  return request({
    url: '/user/logout',
    method: 'post',
  })
}

export function getInfo() {
  return request({
    url: '/user/info',
    method: 'get',
  })
}

export function register(username, nickname, password) {
  const data = {
    username,
    nickname,
    password
  }
  return request({
    url: '/register',
    method: 'post',
    data
  })
}
