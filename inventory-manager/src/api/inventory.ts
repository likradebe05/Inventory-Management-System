import { api } from './axios'

export type Item = {
    productId: string
    name: string
    sku?: string
    quantity: number
    location?: string
    price: number
    categoryId?: string
    supplierId?: string
    imageData?: string // Base64 or binary data indicator
    imageContentType?: string
    originalImageName?: string
}

export async function getItems() {
    const { data } = await api.get('/inventory')
    return data as Item[]
}

export async function createItem(payload: Partial<Item>) {
    const { data } = await api.post('/inventory', payload)
    return data as Item
}

export async function createItemWithImage(formData: FormData) {
    const { data } = await api.post('/inventory/create-with-image', formData, {
        headers: {
            'Content-Type': 'multipart/form-data',
        },
    })
    return data as Item
}

export async function updateItem(id: string, payload: Partial<Item>) {
    const { data } = await api.put(`/inventory/${id}`, payload)
    return data as Item
}

export async function updateItemWithImage(id: string, formData: FormData) {
    const { data } = await api.put(`/inventory/update-with-image/${id}`, formData, {
        headers: {
            'Content-Type': 'multipart/form-data',
        },
    })
    return data as Item
}

export async function deleteItem(id: string) {
    const { data } = await api.delete(`/inventory/${id}`)
    return data as { ok: boolean }
}

// Helper function to get the full image URL
export function getProductImageUrl(product: Item): string {
    // Check if product has image data
    if (product.imageData || product.originalImageName) {
        // Return the BLOB endpoint URL
        return `http://localhost:8088/inventory/${product.productId}/image`;
    }
    
    // Fallback to default image
    return '/images/main.png';
}
