<template>
  <div id="app">
    <!--    <div id="nav">
      <router-link to="/">Home</router-link> |
      <router-link to="/about">About</router-link>
    </div>-->
    <router-view/>
  </div>
</template>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  /*text-align: center;*/
  color: #2c3e50;
}

#nav {
  padding: 30px;
}

#nav a {
  font-weight: bold;
  color: #2c3e50;
}

#nav a.router-link-exact-active {
  color: #42b983;
}

/*
  这里不要用星号（*）来代替这一长串。
  这里如果用*的话，会导致所有元素都无法覆盖这里的默认设置
*/
body, h1, h2, h3, h4, h5, h6, ul, li, ol, dl, dt, dd,
p, span, div, object, iframe, pre, a, abbr, cite,
input, button, select, option {
  margin: 0;
  padding: 0;
  font-weight: normal;
  box-sizing: border-box;
  -moz-box-sizing: border-box;
  -webkit-box-sizing: border-box;
}

body {
  background: #f5f6f7;
}

</style>
