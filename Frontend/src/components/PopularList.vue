<template>
  <div class="row justify-content-center" >
    <div class="popular row justify-content-center">
			<!-- component화 예정 -->
      <router-link to="/searched?q=전민동" class="cube">
        <div class="flippety" style="background: url('https://d2t7cq5f1ua57i.cloudfront.net/images/r_images/51592/53573/51592_53573_80_0_4735_2017223173745426.jpg')" />
        <div class="flop">
          <h3 class="dong">전민동</h3>
        </div>
      </router-link>

      <router-link to="/searched?q=둔산동" class="cube">
        <div class="flippety" style="background: url('https://lh3.googleusercontent.com/proxy/wJasrhLIEzGLFVUB-rf8vgVKJBfkFxhq35mtD84qPgXa1uMuWdD4Teo61jrJNvlR-zI6aWKdhD5hHnA4WPpt4l-hYtHSmriuvV0YdwLryLk9y0wNDGNRzlqIF6xcd8dfV2pQ1wEmi47T742-hO5QpmMmZEVtrs4WO3LjWW7sMsw02FUV3u4JH8D_WZ5D53ojfv4CCEWMMzgaXWPvbh_UxjWAT7QdOrkUI_Uqr-_NaExe1veGYBwQeCr0Eqx4ShtSMWfkBGHAiMACmPfTKHt5zrk3rtxWht521Xko_zE2hCb0')" />
        <div class="flop">
          <h3 class="dong">둔산동</h3>
        </div>
      </router-link>


      <router-link to="/searched?q=궁동" class="cube">
        <div class="flippety" style="background: url('https://img1.daumcdn.net/thumb/R720x0.q80/?scode=mtistory2&fname=http%3A%2F%2Fcfile25.uf.tistory.com%2Fimage%2F9942034F5DBFF791198F4E')" />
        <div class="flop">
          <h3 class="dong">궁동</h3>
        </div>
      </router-link>


      <router-link to="/searched?q=탄방동" class="cube">
        <div class="flippety" style="background: url('https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcRX70KDX-mDFhqCjKtfH5p7qDghoVuGmbkJh5XwM-VADX45GY_R')" />
        <div class="flop">
          <h3 class="dong">탄방동</h3>
        </div>
      </router-link>


      <router-link to="/searched?q=오류동" class="cube">
        <div class="flippety" style="background: url('https://lh3.googleusercontent.com/proxy/pS6Ob62ZUVNyeYGulhPZSmQlIM_J8ibtla_T_H9-cJzT_lz6u69sIm4jyIneMnJ-Y2MLVn_S7o6bwvhol2r_3Cr3ltxFWOD3hSukNBiQVOXKKU4xtuCOxawaY6qTXZ-Jerozb5ko6UnLnSYNF3RlLdo_evsfK7PMqsSF10zhauUjzOQGz-_68vShfp4d-vj3cgaYAZDk_27jXDS7F4WyACfsxLcso5LUj_xEtRwdh7uNP_mORN55xGq3FpIbEDHs7WT17PsClLt-SqMGH3-_BwuANjLqA5h1')" />
        <div class="flop">
          <h3 class="dong">오류동</h3>
        </div>
      </router-link>


      <router-link to="/searched?q=덕명동" class="cube">
        <div class="flippety" style="background: url('https://lh3.googleusercontent.com/proxy/UAcEXnDvsYq_VrPxbb1iohEq-2_tvRpBdCH8kkLhdJ-CRCsNaBSRP61RtI9kWMK6A47yYfvM1RBx4wSR5d7ydoBxWy_CmgKIZptLfbIrU8xYMATK_yWVSlskxmf2C51kFOfS7AP66OK-yaYafNxxfZbt4_VciNaJmxt69pptdpUu99DzBXvgSGEnUPZOJDW5dMawhpA')" />
        <div class="flop">
          <h3 class="dong">덕명동</h3>
        </div>
      </router-link>


    </div>
  </div>
</template>

<script>
export default {};
</script>

<style>
.popular {
  width: 80%;
  min-width: 480px;
  text-align: left;
  padding: 3rem;
  background: white;
}

.cube {
  width: 150px;
	text-align: center;
  height: 150px;
  transition: transform 0.33s;
  transform-style: preserve-3d;
	cursor: pointer;
}

.flippety,
.flop {
  height: 150px;
	align-items: center !important;
	justify-content: center !important;
	display: flex;
	border-radius: 5px;
	background: #ffffff;
}

.flippety {
  transform: translateZ(75px);
}

.flop {
  transform: rotateX(-90deg) translateZ(-75px);
	background-size: cover !important;
}

.cube:hover {
  -webkit-transform: rotateX(90deg);
  transform: rotateX(90deg);
}

.dong {
	font-size: 1.4rem;
}
</style>