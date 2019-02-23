import { Connect, SimpleSigner } from 'uport-connect'

export const uport = new Connect('shiki_tak\'s demo app', {
  clientId: '2ozXmhW7b5fSdHpKC9pEgK5yW79cxQLRgqn',
  network: 'rinkeby',
  signer: SimpleSigner('0a5f64048cad2420c2799c2d59a7e595c437bba94679c0080f6edafc6e7c3169')
})