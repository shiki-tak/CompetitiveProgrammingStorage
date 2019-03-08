<template>
  <div>
    <v-container
      grid-list-xl
      align-center
    >
      <div class="main" style="text-align: center">
        <app-logo/>
        <h1 class="title">
          Yama Lover's Word
        </h1>
        <div class="links">
          <a
            href="http://127.0.0.1:3000/server/auth/twitter"
            class="button--grey">Twitter Login</a>
        </div>
      </div>
      <v-layout v-for="word in words" :key="word.id">
        <v-card
          class="mx-auto"
          color="#26c6da"
          dark
          max-width="600"
          max-height="400"
          style="margin-bottom: 90px; width: 600px; height: 400px;"
        >
          <v-img
            v-bind:src="word.image_url"
          style="width: 600px; height: 400px;"
          >
            <v-card-title>
              <v-icon
                large
                left
              >
                mdi-twitter
              </v-icon>
            </v-card-title>

            <v-card-text class="headline font-weight-bold">
              {{ word.text }}
            </v-card-text>

            <v-card-actions>
              <v-list-tile class="grow">
                <v-list-tile-avatar color="grey darken-3">
                  <v-img
                    class="elevation-6"
                    src="https://avataaars.io/?avatarStyle=Transparent&topType=ShortHairShortCurly&accessoriesType=Prescription02&hairColor=Black&facialHairType=Blank&clotheType=Hoodie&clotheColor=White&eyeType=Default&eyebrowType=DefaultNatural&mouthType=Default&skinColor=Light"
                  ></v-img>
                </v-list-tile-avatar>

                <v-list-tile-content>
                  <v-list-tile-title>{{ word.user_name }}</v-list-tile-title>
                </v-list-tile-content>

                <v-layout
                  align-center
                  justify-end
                >
                </v-layout>
              </v-list-tile>
            </v-card-actions>
          </v-img>
        </v-card>
      </v-layout>
    </v-container>
  </div>
</template>

<script>
import AppLogo from '~/components/AppLogo.vue'

export default {
  components: {
    AppLogo
  },
  data() {
    return {
      words: [
        {
          id: 0,
          text: "そこに山があるから。",
          user_name: "ジョージ・マロリー",
          image_url: "https://yamabluesky.files.wordpress.com/2019/01/slide01-2.jpg"
        },
        {
          id: 1,
          text: "山はこれ以上大きくならないが、私たちはいくらでも成長できる。",
          user_name: "エドモンド・ヒラリー",
          image_url: "https://yamabluesky.files.wordpress.com/2019/01/pexels-photo-26419.jpg"
        },
        {
          id: 3,
          text: "登頂がすべてではない。登山をするということすべてを楽しみたい。",
          user_name: "竹内洋岳 ",
          image_url: "https://yamabluesky.files.wordpress.com/2019/01/mountkinabalu-1024x682.jpg"
        },
        {
          id: 4,
          text: "目の前の山に登りたまえ。山は君の全ての疑問に答えてくれるだろう。",
          user_name: "ラインポルト・メスナー",
          image_url: "https://yamabluesky.files.wordpress.com/2019/01/1024px-mount_aino_fom_mount_kita_2001-7-2.jpg"
        },
        {
          id: 5,
          text: "単独行者よ、強くなれ！",
          user_name: "加藤文太郎",
          image_url: "https://yamabluesky.files.wordpress.com/2019/01/75d1d9e2.jpg"
        }        
      ]
    }
  }
}
</script>

<style>
.title {
  font-family: "Quicksand", "Source Sans Pro", -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, "Helvetica Neue", Arial, sans-serif; /* 1 */
  display: block;
  font-weight: 300;
  font-size: 100px;
  color: #35495e;
  letter-spacing: 1px;
}

.links {
  padding-top: 15px;
}

.main {
  margin-bottom: 5%;
}

.v-card__actions {
    position: absolute;
    bottom: 0;
}
</style>

