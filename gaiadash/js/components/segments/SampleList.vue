<template>
  <div>
  <ol>
    <li v-for="todo in todos">
      {{ todo.text }}
    </li>
  </ol>
  </div>
</template>

<script>
  export default {
  data: function(){
  return {
  todos:[
  { text: 'vue test for qqe' },
  { text: 'vue test for gaia' },
  { text: 'vue test for gaia' }
    ]
  }
  }
  };
</script>
