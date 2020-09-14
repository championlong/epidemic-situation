<template>
    <div>
        <baidu-map class="map" :center="map.center" :zoom="map.zoom" @ready="handler">
            <!--缩放-->
            <bm-navigation anchor="BMAP_ANCHOR_TOP_LEFT"></bm-navigation>
            <!--定位-->
            <bm-geolocation anchor="BMAP_ANCHOR_BOTTOM_RIGHT" :showAddressBar="true" :autoLocation="true"></bm-geolocation>
            <!--点-->
            <bm-marker :position="map.center" :dragging="map.dragging" animation="BMAP_ANIMATION_DROP">
                <!--提示信息-->
                <bm-info-window :show="map.show">Hello~</bm-info-window>
            </bm-marker>
        </baidu-map>
    </div>
</template>
 
<script>
    export default {
        name: "demo",
        data: () => ({
            map:{
                center: {lng: 121.4472540000, lat: 31.3236200000},
                zoom: 15,
                show: true,
                dragging: true
            },
        }),
        methods: {
            handler ({BMap, map}) {
                let me = this;
                console.log(BMap, map)
                // 鼠标缩放
                map.enableScrollWheelZoom(true);
                // 点击事件获取经纬度
                map.addEventListener('click', function (e) {
                    console.log(e.point.lng, e.point.lat)
                })
            }
        }
    }
</script>
 
<style scoped>
    .map {
        width: 100%;
        height: 400px;
    }
</style>