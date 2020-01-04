<template>
  <h1>
    <a :href="'#' + title">{{ title }}</a>
  </h1>
</template>

<script>
export default {
  data() {
    return {
      title: "Title"
    };
  }
};
</script>

<style scoped></style>
