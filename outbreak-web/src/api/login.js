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

export const getCode = (email) => {
  const data = {
      email
  }
  return request({
    url: '/user/code',
    method: 'get',
    params: data
  })
}

export function register(user,verify) {
  const data = {
    user,
    verify,
  }
  return request({
    url: '/user/register',
    method: 'post',
    data
  })
}
