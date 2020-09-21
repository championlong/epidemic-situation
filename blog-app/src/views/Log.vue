<template>
  <div class="only">

    <demo></demo>

  </div>
</template>


<script>
  import demo from '@/components/map_index'
  export default {
    name: 'Log',
    data() {
      return {}
    },
    components: {
      'demo': demo,
    }
  }
</script>

<style scoped>
  .only {
    width: 100%;
    margin:0px;
    margin-top:60px;
    /* transform:translateY(60px); */
  }
</style>
