// src/api/customers.ts
import { api } from './axios';
import { AxiosError } from 'axios';
import { saveToken } from '@/utils/auth';

// Type definitions for the API responses
type Product = {
    productId: string;
    name: string;
    price: number;
    inStock: number;
}

type Warehouse = {
    warehouseId: string;
    name: string;
    location: string;
}

type Category = {
    categoryId: string;
    name: string;
    productCount: number;
}

type Order = {
    orderId: string;
    customerId: string;
    orderDate: string; // Mapped from java.util.Date
    itemsOrdered: string;
    amountPaid: number; // Mapped from double
    orderItems: string;
    productIds: number[]; // Mapped from List<Long>
    status: string;
    deliveryAddress: string;
    paymentMethod: string;
}

export async function loginCustomer(payload: { email: string, password: string }) {
    try {
        const { data } = await api.post('/customer/login', payload);

        // No longer expecting token - just user info
        if (!data || !data.user) {
            throw new Error('Login failed - invalid response.');
        }

        // Store user info in localStorage instead of token
        if (typeof window !== 'undefined') {
            localStorage.setItem('currentUser', JSON.stringify(data.user));
        }

        // Return the user data
        return data as { user: { userId: string, email: string, firstName: string, lastName: string }, message: string };

    } catch (error: any) {
        const axiosError = error as AxiosError;

        if (axiosError.response) {
            if (axiosError.response.status === 401) {
                const errorMessage = (axiosError.response.data as any)?.error || 'Invalid email or password';
                throw new Error(errorMessage);
            }
        }

        throw new Error('An unexpected error occurred. Please try again later.');
    }
}

export async function signupCustomer(payload: {
    firstName: string;
    lastName: string;
    email: string;
    password: string;
    address: string;
    cellphone: string;
}) {
    try {
        const { data } = await api.post('/customer/create', payload);
        
        // Store user info in localStorage instead of token
        if (data.user && typeof window !== 'undefined') {
            localStorage.setItem('currentUser', JSON.stringify(data.user));
        }
        
        return data as { user: { userId: string; firstName: string; lastName: string; email: string } };
    } catch (error: any) {
        const axiosError = error as AxiosError;
        if (axiosError.response) {
            const errorMessage = (axiosError.response.data as any)?.error || 'Signup failed';
            throw new Error(errorMessage);
        }
        throw new Error('An unexpected error occurred during signup.');
    }
}

// Customer self-service functions
export async function getCustomerAccount(customerId: string) {
    try {
        const { data } = await api.get(`/customer/account/${customerId}`);
        return data as {
            userId: string;
            firstName: string;
            lastName: string;
            email: string;
            address: string;
            cellphone: string;
        };
    } catch (error: any) {
        const axiosError = error as AxiosError;
        if (axiosError.response?.status === 404) {
            throw new Error('Customer not found');
        }
        throw new Error('Failed to fetch account information');
    }
}

export async function getCustomerOrders(customerId: string) {
    try {
        const { data } = await api.get(`/customer/orders/${customerId}`);
        return data as {
            customerId: string;
            orders: Array<{
                orderId: string;
                orderDate: string;
                status: string;
                amountPaid: number;
                deliveryAddress: string;
                paymentMethod: string;
                itemCount: number;
            }>;
            totalOrders: number;
        };
    } catch (error: any) {
        const axiosError = error as AxiosError;
        if (axiosError.response?.status === 404) {
            throw new Error('Customer not found');
        }
        throw new Error('Failed to fetch order history');
    }
}

export async function getCustomerOrderDetails(customerId: string, orderId: string) {
    try {
        const { data } = await api.get(`/customer/orders/${customerId}/${orderId}`);
        return data as {
            orderId: string;
            orderDate: string;
            status: string;
            amountPaid: number;
            deliveryAddress: string;
            paymentMethod: string;
            customer: {
                customerId: string;
                firstName: string;
                lastName: string;
                email: string;
            };
            items: Array<{
                productId: string;
                productName: string;
                price: number;
                quantity: number;
                subtotal: number;
            }>;
        };
    } catch (error: any) {
        const axiosError = error as AxiosError;
        if (axiosError.response?.status === 404) {
            throw new Error('Order not found');
        }
        if (axiosError.response?.status === 403) {
            throw new Error('Access denied - this order does not belong to you');
        }
        throw new Error('Failed to fetch order details');
    }
}

export async function updateCustomerAccount(customerId: string, updates: {
    firstName?: string;
    lastName?: string;
    address?: string;
    cellphone?: string;
    password?: string;
}) {
    try {
        const { data } = await api.put(`/customer/account/${customerId}`, updates);
        
        // Update stored user info if it exists
        if (typeof window !== 'undefined') {
            const currentUser = localStorage.getItem('currentUser');
            if (currentUser) {
                const user = JSON.parse(currentUser);
                const updatedUser = { ...user, ...updates };
                delete updatedUser.password; // Don't store password
                localStorage.setItem('currentUser', JSON.stringify(updatedUser));
            }
        }
        
        return data as {
            userId: string;
            firstName: string;
            lastName: string;
            email: string;
            address: string;
            cellphone: string;
            message: string;
        };
    } catch (error: any) {
        const axiosError = error as AxiosError;
        if (axiosError.response?.status === 404) {
            throw new Error('Customer not found');
        }
        throw new Error('Failed to update account');
    }
}

export async function deleteCustomerAccount(customerId: string) {
    try {
        const response = await api.delete(`/customer/${customerId}`);
        return response.data as {
            message: string;
        };
    } catch (error: any) {
        const axiosError = error as AxiosError;
        if (axiosError.response?.status === 404) {
            throw new Error('Customer not found');
        }
        throw new Error('Failed to delete account');
    }
}

// New public data functions for the landing page
export async function getProducts() {
    const { data } = await api.get('/products');
    return data as Product[];
}

export async function getWarehouses() {
    const { data } = await api.get('/warehouses');
    return data as Warehouse[];
}

export async function getCategories() {
    const { data } = await api.get('/categories');
    return data as Category[];
}