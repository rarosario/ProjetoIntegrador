# ProjetoIntegrador

## Grupo 5 Wave - 3


### Requisito 6


### POST 6.1 - Shipping - Insert

Para fazer um cadastro de shipping no sistema é necessário efetuar uma requisição via postman

```
{
    "shippingId": "Envio-001",
    "warehouseCode": "ARM-001",
    "buyerId": 1,
    "productId": "MLB-129",
    "cep": "55641-715"
}
```

***Link para uso:***

http://localhost:8090/api/v1/Shipping/insert

### POST 6.2 - States - Insert

Para fazer um cadastro de States no sistema é necessário efetuar uma requisição via postman

```
{
    "cep": "69921-000",
    "nome": "Acre",
    "valorFrete": 400.50 
}
```

***Link para uso:***

http://localhost:8090/api/v1/States/insert



### GET 6.3 - Shipping - list

Para obter uma lista de shipping cadastrados

***Link para uso:***

http://localhost:8090/api/v1/Shipping/Envio-001

Exemplo de lista de produtos

```
[
    {
        "shippingId": "Envio-001",
        "warehouseCode": "ARM-001",
        "buyerId": "Alessandro",
        "productId": "Caixa de Banana",
        "cep": "55641-715",
        "nome": "DistritoFederal",
        "valorFrete": 690.8
    }
]
```
