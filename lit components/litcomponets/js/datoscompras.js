// app.js
import { LitElement, html, css } from 'lit';

class DatosCompras extends LitElement {
  static styles = css`
    body {
      font-family: 'Arial', sans-serif;
      background-image: url('img/pngtree-an-empty-supermarket-on-blue-walls-has-shelves-of-products-image_2617234.jpg');
      background-size: cover;
      background-position: center;
      background-repeat: no-repeat;
      display: flex;
      flex-direction: column;
      justify-content: center;
      align-items: center;
    }

    .form-container {
      background-color: rgba(255, 255, 255, 0.8);
      padding: 20px 30px;
      border-radius: 10px;
      box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
      width: 65vh;
      display: grid;
      gap: 20px;
      margin-top: 4vh;
    }

    form p {
      font-weight: bold;
      margin-bottom: 5px;
      color: #333;
    }

    form label {
      font-weight: bold;
      margin-bottom: 5px;
      color: #333;
      display: block;
    }

    select,
    input[type='text'],
    input[type='number'] {
      width: 100%;
      padding: 10px;
      margin-bottom: 15px;
      border: 1px solid #ddd;
      border-radius: 5px;
      font-size: 14px;
      color: #555;
      box-sizing: border-box;
    }

    select:focus,
    input[type='text']:focus,
    input[type='number']:focus {
      border-color: #007BFF;
      outline: none;
      box-shadow: 0 0 5px rgba(0, 123, 255, 0.2);
    }

    .datos {
      display: flex;
      gap: 10px;
    }

    .datos p,
    .datos input {
      flex: 1;
    }

    button {
      width: 100%;
      padding: 10px;
      background-color: #007BFF;
      color: #fff;
      border: none;
      border-radius: 5px;
      font-size: 16px;
      cursor: pointer;
    }

    button:hover {
      background-color: #0056b3;
      transition: background-color 0.3s ease;
    }
  `;

  constructor() {
    super();
    this.numerofactura = Math.floor(Math.random() * 100000);
  }

  render() {
    return html`
      <div class="form-container">
        <form id="user-form">
          <p>Factura No.</p>
          <input type="text" name="factura" id="factura" .value="${this.numerofactura}" disabled />

          <p>ID</p>
          <input type="text" name="id" id="id" />

          <div class="datos">
            <p>Nombre</p>
            <input type="text" name="nombre" id="nombre" />

            <p>Apellido</p>
            <input type="text" name="apellido" id="apellido" />
          </div>

          <p>Dirección</p>
          <input type="text" name="direccion" id="direccion" />

          <p>Email</p>
          <input type="text" name="email" id="email" />

        </form>
      </div>
    `;
  }
}

// Definir el componente
customElements.define('datos-compras', DatosCompras);
