import loadScriptOnce from 'load-script-once'
import axios from 'axios'
// clusterer: 마커를 클러스터링 할 수 있는 클러스터러 라이브러리 입니다.
// services: 장소 검색 과 주소-좌표 변환 을 할 수 있는 services 라이브러리 입니다.
// drawing: 지도 위에 마커와 그래픽스 객체를 쉽게 그릴 수 있게 그리기 모드를 지원하는 drawing 라이브러리 입니다.

//발급받은 App key
const {VUE_APP_KAKAO_REST_API_KEY} = process.env
//Kakao Map API js get
const DAUM_KAKAO_MAP_LIB_URL = `//dapi.kakao.com/v2/maps/sdk.js?appkey=${VUE_APP_KAKAO_REST_API_KEY}&libraries=drawing,clusterer,services&autoload=false`


class Map {
    constructor() {
        this.map = null
        this.marker = null
        this.cafes = null
        Map.initialize()
    }

    async mount(elementId, curLatitude, curLongitude) {
        await Map.initialize()

        // re-use map
        if (Map.cachedMaps[elementId]) {
            this.map = Map.cachedMaps[elementId]
            const oldElement = this.map.getNode()
            const newElement = document.getElementById(elementId)
            newElement.parentNode.replaceChild(oldElement, newElement)

            // create map
        } else {
            //지도 생성
            this.map = new Map.daum.maps.Map(
                document.getElementById(elementId), {
                    center: new Map.daum.maps.LatLng(curLatitude, curLongitude),
                    level: 3,
                    mapTypeId : Map.daum.maps.MapTypeId.ROADMAP // 지도종류
                },
            )

            this.marker = new Map.daum.maps.Marker({
                position: new Map.daum.maps.LatLng(curLatitude, curLongitude), // 마커의 좌표
                map: this.map // 마커를 표시할 지도 객체
            })
   
            //Add Zoom In/Out Event Listener
            Map.daum.maps.event.addListener(this.map, 'zoom_changed', function(){
                console.log("lat : " + curLatitude)
                console.log("lng : " + curLongitude)

                axios.post("http://192.168.31.111:3000/v1/map/",{
                            'longitude': curLongitude,
                            'latitude': curLatitude,
                            'level': this.getLevel()
                    })
                    .then(res => {
                        // let cafes = null
                        if(typeof res.data !== String){
                            console.log(typeof res.data === String)
                            // cafes.forEach(cafe => {
                            //     console.log(cafe.id)
                            // })
                        }
                       
                    })
                    .catch(err => {
                        console.log(err)
                    });
            })
            
            this.map.setCopyrightPosition(Map.daum.maps.CopyrightPosition.BOTTOMRIGHT, true)
            this.map.clusters = {}
            this.map.markersWithoutCluster = []
            
            Map.cachedMaps[elementId] = this.map
        }
        return this
    }

    addMarkerClusters(clusterSpecs = []) {
        clusterSpecs.forEach(({
            key,
            color,
            zIndex = 0,
            singleIconURL = null
        }) => {
            if (this.map.clusters[key]) return

            const cluster = this.map.clusters[key] = new Map.daum.maps.MarkerClusterer({
                map: this.map,
                averageCenter: true,
                gridSize: 30,
                minClusterSize: 2,
                minLevel: 10,
                disableClickZoom: false,
                calculator: [10, 20], // 0~9, 10~19, 20~
                styles: [{
                        width: '30px',
                        height: '30px',
                        background: color,
                        opacity: 0.95,
                        border: '2px solid white',
                        borderRadius: '100%',
                        color: 'white',
                        textAlign: 'center',
                        lineHeight: '27px',
                        fontSize: '20px',
                        fontWeight: 'bold',
                    },
                    {
                        width: '36px',
                        height: '36px',
                        background: color,
                        opacity: 0.95,
                        border: '2px solid white',
                        borderRadius: '100%',
                        color: 'white',
                        textAlign: 'center',
                        lineHeight: '33px',
                        fontSize: '22px',
                        fontWeight: 'bold',
                    },
                    {
                        width: '48px',
                        height: '48px',
                        background: color,
                        opacity: 0.95,
                        border: '2px solid white',
                        borderRadius: '100%',
                        color: 'white',
                        textAlign: 'center',
                        lineHeight: '44px',
                        fontSize: '25px',
                        fontWeight: 'bold',
                    },
                ],
            })

            cluster._icon = new Map.daum.maps.MarkerImage(
                singleIconURL,
                new Map.daum.maps.Size(15, 15),
            )

            cluster._zIndex = zIndex
        })
        return this
    }
 
    addMarkers(markerSpecs = []) {
        const markerSpecsWithoutClusterKey = []
        const markerSpecsByClusterKey = markerSpecs.reduce((result, spec) => {
            if (!spec.clusterKey) {
                markerSpecsWithoutClusterKey.push(spec)
                return result
            }
            if (!result[spec.clusterKey]) {
                result[spec.clusterKey] = []
            }
            result[spec.clusterKey].push(spec)
            return result
        }, {})

        markerSpecsWithoutClusterKey.forEach(({
            lat,
            lng,
            title = null,
            onClick = null
        }) => {
            const marker = new Map.daum.maps.Marker({
                map: this.map,
                position: new Map.daum.maps.LatLng(lat, lng),
                title,
            })

            if (onClick) {
                Map.daum.maps.event.addListener(marker, 'click', onClick)
            }

            this.map.markersWithoutCluster.push(marker)
        })

        for (let clusterKey in markerSpecsByClusterKey) {
            const cluster = this.map.clusters[clusterKey]
            cluster.addMarkers(
                markerSpecsByClusterKey[clusterKey].map(({
                    lat,
                    lng,
                    title = null,
                    onClick = null
                }) => {
                    const marker = new Map.daum.maps.Marker({
                        title,
                        position: new Map.daum.maps.LatLng(lat, lng),
                        image: cluster._icon,
                        zIndex: cluster._zIndex,
                    })

                    if (onClick) {
                        Map.daum.maps.event.addListener(marker, 'click', onClick)
                    }

                    return marker
                })
            )
        }
    }

    clearMarkers() {
        // remove cluster markers
        for (let k in this.map.clusters) {
            const cluster = this.map.clusters[k]
            cluster.clear()
        }

        this.map.markersWithoutCluster.forEach(marker => {
            marker.setMap(null)
        })

        this.map.markersWithoutCluster = []
    }

    setCenter({
        lat,
        lng,
        maxLevel = 8
    }) {
        if (this.map.getLevel() > maxLevel) {
            this.map.setLevel(maxLevel)
        }
        this.map.panTo(
            new Map.daum.maps.LatLng(lat, lng)
        )
    }

    getLevel(){
        console.log("현재 지도 확대 레벨 : " + this.map.getLevel())
        return this.map.getLevel()
    }
}

Map.cachedMaps = {}
Map.daum = null
Map.initialize = function () {
    return new Promise((resolve, reject) => {
        loadScriptOnce(DAUM_KAKAO_MAP_LIB_URL, (err) => {
            if (err) return reject(err)
            Map.daum = window.daum
            Map.daum.maps.load(() => resolve())
        })
    })
}

export default Map