const CREATION_TOKEN = Symbol("Config creation token");

/**
 * Configuración única por módulo y proceso de Node.js.
 * Se obtiene mediante getInstance para evitar crear copias independientes.
 */
export class Config {
  static #instance;

  #url;
  #port;

  constructor(token, { url, port }) {
    if (token !== CREATION_TOKEN) {
      throw new Error("Usá Config.getInstance() para obtener la configuración.");
    }

    this.#url = url;
    this.#port = port;
    Object.freeze(this);
  }

  static getInstance(options = {}) {
    if (!Config.#instance) {
      const configuration = {
        url: options.url ?? "http://localhost",
        port: options.port ?? 3000,
      };

      Config.#instance = new Config(CREATION_TOKEN, configuration);
    }

    return Config.#instance;
  }

  get url() {
    return this.#url;
  }

  get port() {
    return this.#port;
  }
}
