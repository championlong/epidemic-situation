import request from '@/request'

export const getList = (page,pageSize) => {
  const params = {
    page,
    pageSize
  }
  return request({
    url: "/dynameic/getList",
    method: 'get',
    params
  })
}
