import request from '@/request'

export const getChinaList = () => {
  return request({
    url: "/index/getChinaList",
    method: 'post',
  })
}

export const getTotalData = () => {
  return request({
    url: "/index/getTotalData",
    method: 'post',
  })
}
