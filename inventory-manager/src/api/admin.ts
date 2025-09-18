import { api } from './axios'
import type { Item } from './inventory'

// Admin user types
export type AdminUser = {
    userId: string
    firstName: string
    lastName: string
    email: string
    role: string
    createdAt?: string
    updatedAt?: string
}

export type CreateAdminPayload = {
    firstName: string
    lastName: string
    email: string
    password: string
}

// Admin Dashboard Statistics
export type DashboardStats = {
    totalProducts: number
    totalCustomers: number
    totalOrders: number
    totalAdmins: number
    lowStockProducts: number
    recentOrders: number
}

// ===== ADMIN USER MANAGEMENT =====

/**
 * Get all admin users
 */
export async function getAdminUsers() {
    const { data } = await api.get('/admin/users')
    return data as AdminUser[]
}

/**
 * Create a new admin user
 */
export async function createAdminUser(payload: CreateAdminPayload) {
    const { data } = await api.post('/admin/users', payload)
    return data as AdminUser
}

/**
 * Update admin user information
 */
export async function updateAdminUser(userId: string, payload: Partial<CreateAdminPayload>) {
    const { data } = await api.put(`/admin/users/${userId}`, payload)
    return data as AdminUser
}

/**
 * Delete admin user
 */
export async function deleteAdminUser(userId: string) {
    const { data } = await api.delete(`/admin/users/${userId}`)
    return data as { ok: boolean }
}

// ===== PRODUCT MANAGEMENT (Admin Enhanced) =====

/**
 * Get all products with admin privileges (includes more details)
 */
export async function getAllProducts() {
    const { data } = await api.get('/admin/products')
    return data as Item[]
}

/**
 * Get product by ID
 */
export async function getProductById(productId: string) {
    const { data } = await api.get(`/admin/products/${productId}`)
    return data as Item
}

/**
 * Create product with image upload
 */
export async function createProductWithImage(formData: FormData) {
    const { data } = await api.post('/admin/products', formData, {
        headers: {
            'Content-Type': 'multipart/form-data',
        },
    })
    return data as Item
}

/**
 * Update product with optional image upload
 */
export async function updateProductWithImage(productId: string, formData: FormData) {
    const { data } = await api.put(`/admin/products/${productId}`, formData, {
        headers: {
            'Content-Type': 'multipart/form-data',
        },
    })
    return data as Item
}

/**
 * Delete product
 */
export async function deleteProduct(productId: string) {
    const { data } = await api.delete(`/admin/products/${productId}`)
    return data as { ok: boolean }
}

/**
 * Get product image URL for BLOB data
 */
export function getAdminProductImageUrl(product: Item): string {
    // Check if product has image data
    if (product.imageData || product.originalImageName) {
        // Return the BLOB endpoint URL
        return `http://localhost:8088/admin/products/${product.productId}/image`;
    }
    
    // Fallback to default image
    return '/images/main.png';
}

/**
 * Bulk delete products
 */
export async function bulkDeleteProducts(productIds: string[]) {
    const { data } = await api.post('/admin/products/bulk-delete', { productIds })
    return data as { deletedCount: number }
}

// ===== DASHBOARD STATISTICS =====

/**
 * Get dashboard statistics for admin overview
 */
export async function getDashboardStats() {
    const { data } = await api.get('/admin/dashboard/stats')
    return data as DashboardStats
}

// ===== CUSTOMER MANAGEMENT =====

/**
 * Get all customers (admin view)
 */
export async function getAllCustomers() {
    const { data } = await api.get('/admin/customers')
    return data as Array<{
        userId: string
        firstName: string
        lastName: string
        email: string
        address: string
        cellphone: string
        createdAt?: string
        totalOrders?: number
    }>
}

/**
 * Get customer details by ID
 */
export async function getCustomerById(customerId: string) {
    const { data } = await api.get(`/admin/customers/${customerId}`)
    return data
}

// ===== ORDER MANAGEMENT =====

/**
 * Get all orders (admin view)
 */
export async function getAllOrders() {
    const { data } = await api.get('/admin/orders')
    return data as Array<{
        orderId: string
        customerId: string
        customerName: string
        totalAmount: number
        status: string
        orderDate: string
        items: Array<{
            productId: string
            productName: string
            quantity: number
            price: number
        }>
    }>
}

/**
 * Update order status
 */
export async function updateOrderStatus(orderId: string, status: string) {
    const { data } = await api.put(`/admin/orders/${orderId}/status`, { status })
    return data
}