<template>
  <div id="app">
    <p>{{ count }}</p>
    <button @click="handleIncrement">Increament</button>
    <button @click="handleDecrease">Decrease</button>
    <img src="./assets/logo.png" />
    <router-view />
  </div>
</template>

<script>
export default {
  name: "App",
  computed: {
    count: function() {
      return this.$store.state.count;
    }
  },
  methods: {
    handleIncrement: function() {
      this.$store.commit("increment");
    },
    handleDecrease: function() {
      this.$store.commit("decrease");
    }
  }
};
</script>

<style>
#app {
  font-family: "Avenir", Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
