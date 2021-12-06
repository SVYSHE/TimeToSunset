<template>
  <div>
    <input v-model="city" @click="getTimeToSunset">
    <button @click="getTimeToSunset">WO ZEIT?</button>
    <h2>Time to sunset: {{ timeToSunset }}</h2>
  </div>
</template>

<script>
export default {
  data() {
    return {
      city: "",
      timeToSunset: ""
    }
  },
  methods: {
    getTimeToSunset: async function () {
      console.log("IST IN TIME TO SUNSET");
      try {
        let response = (await fetch("http://127.0.0.1:9001/timetosunset/Bonn")).setHeader("Access-Control-Allow-Origin", "*")
        //console.log(response)
        //let response = await fetch("http://127.0.0.1:9001/timetosunset/Bonn").setHeader("Access-Control-Allow-Origin", "*");
        //console.log(asyncresponse)
        console.log("ES WURDE GEFETCHT")
        if (response.ok) {
          console.log("OK")
        } else {
          console.log("KAPUTT");
          alert("IST KAPUTT: " + response.status);
        }
      } catch (e) {
        console.log(e);
      }

    }
  }
}
</script>

<style>

</style>