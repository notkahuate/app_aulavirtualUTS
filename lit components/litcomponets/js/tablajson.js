class tablason extends HTMLElement {
    constructor(){
        super();
    }

    connectedCallback(){
        this.innerHTML =`

        <style>
        .summary-container {
            margin-top: 2vh;
            background-color: rgba(255, 255, 255, 0.8);
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
            width: 65vh;
            display: flex;
            flex-direction: column;
        }

        .summary-container h3 {
            text-align: center;
            font-weight: bold;
            color: #333;
        }

        .summary-table {
            width: 100%;
            border-collapse: collapse;
        }

        .summary-table th, .summary-table td {
            padding: 10px;
            text-align: center;
            border: 1px solid #ddd;
        }

        .summary-table th {
            background-color: #007BFF;
            color: #fff;
        }

        </style>

        <div class="summary-container">
            <h3>Resumen de Compra</h3>
            <table class="summary-table">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Producto</th>
                        <th>Cantidad</th>
                        <th>Total</th>
                    </tr>
                </thead>
                <tbody id="summary-body">
                   
                </tbody>
            </table>
        </div>


        `;
    }
    

}


customElements.define('tabla-json',tablason);