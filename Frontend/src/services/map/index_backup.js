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
        this.makers = null
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
                title: '나의 현재 위치',
                map: this.map // 마커를 표시할 지도 객체
            })
            


            //Add Zoom In/Out Event Listener
            Map.daum.maps.event.addListener(this.map, 'zoom_changed', function(){
                console.log("lat : " + curLatitude)
                console.log("lng : " + curLongitude)
                console.log(this.getLevel())
                axios.post("http://192.168.31.111:3000/v1/map/",{
                            'longitude': curLongitude,
                            'latitude': curLatitude,
                            'level': this.getLevel()
                    })
                    .then(res => {
                        //받아온 카페 리스트의 개수가 1개 이상일 때
                        this.makers = null
                        let cafePositions = []
                        const imageSrc = "http://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png"
                        if(res.data.constructor == Array){
                            res.data.forEach(function(cafe){
                                cafePositions.push({
                                    title: cafe.cafe_name,
                                    latlng: new Map.daum.maps.LatLng(cafe.latitude, cafe.longitude)
                                })
                                console.log(cafe.cafe_id)
                                console.log(cafe.cafe_name + '\n' +
                                            "latitude : " + cafe.latitude + '\n' +
                                            "longitude : " + cafe.longitude)
                            })
                        }
                        console.log("cafe position length : " + cafePositions.length)
                        //--------------------
                        for (let i = 0; i < cafePositions.length; i ++) {
    
                            // 마커 이미지의 이미지 크기 입니다
                            let imageSize = new Map.daum.maps.Size(24, 35); 
                            
                            // 마커 이미지를 생성합니다    
                            let markerImage = new Map.daum.maps.MarkerImage(imageSrc, imageSize); 
                            
                            // 마커를 생성합니다
                            let marker = new Map.daum.maps.Marker({
                                map: this.map, // 마커를 표시할 지도
                                position: cafePositions[i].latlng, // 마커를 표시할 위치
                                title : cafePositions[i].title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
                                image : markerImage // 마커 이미지 
                            })
                            
                            marker.setMap(this.map)
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
        maxLevel = 3
    }) {
        if (this.map.getLevel() > maxLevel) {
            this.map.setLevel(maxLevel)
        }
        this.map.panTo(
            new Map.daum.maps.LatLng(lat, lng)
        )
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


// -------------Geolocation으로 마커 표시----------------
/*var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
    mapOption = { 
        center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
        level: 10 // 지도의 확대 레벨 
    }; 

var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다

// HTML5의 geolocation으로 사용할 수 있는지 확인합니다 
if (navigator.geolocation) {
    
    // GeoLocation을 이용해서 접속 위치를 얻어옵니다
    navigator.geolocation.getCurrentPosition(function(position) {
        
        var lat = position.coords.latitude, // 위도
            lon = position.coords.longitude; // 경도
        
        var locPosition = new kakao.maps.LatLng(lat, lon), // 마커가 표시될 위치를 geolocation으로 얻어온 좌표로 생성합니다
            message = '<div style="padding:5px;">여기에 계신가요?!</div>'; // 인포윈도우에 표시될 내용입니다
        
        // 마커와 인포윈도우를 표시합니다
        displayMarker(locPosition, message);
            
      });
    
} else { // HTML5의 GeoLocation을 사용할 수 없을때 마커 표시 위치와 인포윈도우 내용을 설정합니다
    
    var locPosition = new kakao.maps.LatLng(33.450701, 126.570667),    
        message = 'geolocation을 사용할수 없어요..'
        
    displayMarker(locPosition, message);
}

// 지도에 마커와 인포윈도우를 표시하는 함수입니다
function displayMarker(locPosition, message) {

    // 마커를 생성합니다
    var marker = new kakao.maps.Marker({  
        map: map, 
        position: locPosition
    }); 
    
    var iwContent = message, // 인포윈도우에 표시할 내용
        iwRemoveable = true;

    // 인포윈도우를 생성합니다
    var infowindow = new kakao.maps.InfoWindow({
        content : iwContent,
        removable : iwRemoveable
    });
    
    // 인포윈도우를 마커위에 표시합니다 
    infowindow.open(map, marker);
    
    // 지도 중심좌표를 접속위치로 변경합니다
    map.setCenter(locPosition);      
}    */