<template>
  <div id="bSection">
		<div class="row b-row justify-content-center align-items-center">
			<div class="col-12 col-lg-4 offset-lg-3 text-left">
				<h2 class="b-row-quote">광고 / 기업회원 문의</h2>
				<p>함께 성장할 파트너를 찾습니다.</p>
			</div>
			<div class="d-none d-lg-flex col">
				<button class="btn-lg b-btn mr-auto shadow">비즈니스 페이지 바로가기</button>
			</div>
		</div>
	</div>
</template>

<script>
export default {

}
</script>

<style>
	#bSection {
		width: 100%;
		height: 150px;
		background: #f0f0f0;
	}
	.b-row {
		margin: auto !important;
		height: 100%;
	}
	.b-row-quote {
		font-weight: 900;
	}
	.b-btn {
		color: #f0f0f0 !important;
		background: #4f4f4f;
		border: 0;
		font-weight: 500;
		border-radius: 8px !important;
		transition: 250ms linear;
	}
	.b-btn:hover {
		background: #f0f0f0;
		font-weight: 900;
		color: orange !important;
		border-radius: 8px;
	}
</style>