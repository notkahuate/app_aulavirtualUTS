import { LitElement, html, css } from 'lit';

class ComprasD extends LitElement {
  static styles = css`
    .form-containerc {
      background-color: rgba(255, 255, 255, 0.8);
      padding: 20px 30px;
      border-radius: 10px;
      box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
      width: 65vh;
      display: grid;
      gap: 20px;
      margin-top: 2vh;
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
    input[type="text"],
    input[type="number"] {
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
    input[type="text"]:focus,
    input[type="number"]:focus {
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
    this.products = [];
    this.acumuladorsubtotal = 0;
  }

  async firstUpdated() {
    await this.loadProducts();
  }

  async loadProducts() {
    try {
      const response = await fetch("http://localhost:3000/productos");
      const product = await response.json();
      this.products = product;
      this.requestUpdate();
    } catch (error) {
      console.error("Error cargando productos:", error);
    }
  }

  handleProductChange(event) {
    const selectedOption = event.target.options[event.target.selectedIndex];
    const code = selectedOption.getAttribute("data-code");
    const price = selectedOption.getAttribute("data-price");
    this.shadowRoot.querySelector("#code").value = code;
    this.shadowRoot.querySelector("#unit-price").value = price;
  }

  handleSubmit(event) {
    event.preventDefault();
    
    const productSelect = this.shadowRoot.querySelector("#product");
    const productName = productSelect.options[productSelect.selectedIndex].text;
    const productCode = this.shadowRoot.querySelector("#code").value;
    const productPrice = parseFloat(this.shadowRoot.querySelector("#unit-price").value);  // Asegúrate de convertir el precio a número
    const productQuantity = parseInt(this.shadowRoot.querySelector("#quantity").value);  // Asegúrate de convertir la cantidad a número

    if (isNaN(productPrice) || isNaN(productQuantity)) {
      alert("Por favor ingresa una cantidad y un precio válidos.");
      return;
    }

    const totalPrice = (productPrice * productQuantity).toFixed(2);
    const newRow = document.createElement("tr");
    newRow.innerHTML = `
      <td>${productCode}</td>
      <td>${productName}</td>
      <td>${productQuantity}</td>
      <td>${totalPrice}</td>
    `;

    this.acumuladorsubtotal += parseFloat(totalPrice);  // Asegúrate de que el acumulador sea float

    const totalPriceparseado = this.acumuladorsubtotal.toFixed(2);  // Asegúrate de tener 2 decimales
    this.shadowRoot.querySelector("#subtotal").value = totalPriceparseado;

    const iv = (parseFloat(totalPriceparseado) * 0.19).toFixed(2);  // Asegúrate de calcular el IVA correctamente
    this.shadowRoot.querySelector("#iva").value = iv;

    const totalfactura = (parseFloat(totalPriceparseado) + parseFloat(iv)).toFixed(2);
    this.shadowRoot.querySelector("#total").value = totalfactura;

    this.shadowRoot.querySelector("#summary-body").appendChild(newRow);

    this.resetForm();
  }

  resetForm() {
    this.shadowRoot.querySelector("#product").value = "";
    this.shadowRoot.querySelector("#code").value = "";
    this.shadowRoot.querySelector("#unit-price").value = "";
    this.shadowRoot.querySelector("#quantity").value = "";
  }

  render() {
    return html`
      <div class="form-containerc">
        <form @submit="${this.handleSubmit}">
          <label for="product">Nombre del Producto</label>
          <select id="product" @change="${this.handleProductChange}">
            <option value="" disabled selected>Selecciona un producto</option>
            ${this.products.map(
              (product) => html`
                <option
                  value="${product.id}"
                  data-code="${product.code}"
                  data-price="${product.precio}"
                >
                  ${product.nombre}
                </option>
              `
            )}
          </select>

          <label for="code">Código</label>
          <input type="text" id="code" disabled />

          <label for="unit-price">Valor Unitario</label>
          <input type="text" id="unit-price" disabled />

          <label for="quantity">Cantidad</label>
          <input type="number" id="quantity" placeholder="Ingrese la cantidad" />

          <button type="submit">
            <img
              src="img/shop-cart-svgrepo-com (1).svg"
              alt="Icono"
              style="width: 20px; height: 20px; margin-right: 10px;"
            />
            Agregar
          </button>
        </form>
      </div>
    `;
  }
}

customElements.define('compras-d', ComprasD);
