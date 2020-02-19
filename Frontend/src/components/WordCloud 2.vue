<template>
	<div>
		<div class="row">
			<div v-for="i in 3" class="col-4" :key="i">#{{i}}해쉬태그</div>
		</div>
		<div class="row">
			<div v-for="i in 9" :key="i">{{ i }}번째 해쉬태그</div>
		</div>
	</div>
</template>

<script>
export default {
	name: 'WordCloud',
	data() {
		return {}
	}
}
</script>

<style>

</style>