<template>
  <div id="home">
    <el-container>
    	<base-header :activeIndex="activeIndex"></base-header>
		  <router-view class="me-container"/>
		</el-container>
  </div>
</template>

<script>
import BaseHeader from '@/views/BaseHeader'

export default {
  name: 'Home',
  data (){
  	return {
  			activeIndex: '/',
  			footerShow:true
  	}
  },
  components:{
  	'base-header':BaseHeader,
  },
  beforeRouteEnter (to, from, next){
  	 next(vm => {
    	vm.activeIndex = to.path
  	})
  },
  beforeRouteUpdate (to, from, next) {
	  if(to.path == '/'){
	  	this.footerShow = true
	  }else{
	  	this.footerShow = false
	  }
	  this.activeIndex = to.path
	  next()
	}
}
</script>

<style>
.me-container{
  margin: 100px auto 140px;
}
</style>
