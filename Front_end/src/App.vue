<template>
  <div id="backcont">
    <router-view/>
<!--    <router-link to="/repos"-->
  </div>
</template>

<script>

</script>

<style>

*{
  margin: 0px;
  padding: 0px;
  box-sizing: border-box;
}

#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

nav {
  padding: 30px;
}

nav a {
  font-weight: bold;
  color: #2c3e50;
}

nav a.router-link-exact-active {
  color: #42b983;
}
</style>
