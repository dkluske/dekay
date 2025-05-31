package io.dkluske.dekay.productsearch.client

import io.dkluske.dekay.productsearch.model.Product

sealed interface ProductSearchClient {
    fun searchByBarcode(barcode: String): List<Product>

    fun searchByQuery(query: String): List<Product>
}