// src/api/orders.ts

import { api } from './axios';

// Define the minimal item shape expected by the backend
// and actually sent by the frontend
type OrderItemInput = {
    productId: string;
    quantity: number;
    price: number;
};

// This matches the backend's expected payload
type OrderPayload = {
    orderItems: OrderItemInput[];
    userId: number;
    deliveryAddress: string;
    paymentMethod: string;
    amountPaid: number;
};

/**
 * Creates a new order on the backend.
 * @param payload - The complete order data.
 * @returns A promise that resolves with the created order data.
 */
export async function createOrder(payload: OrderPayload) {
    try {
        const { data } = await api.post('/order/create', payload);

        return data;
    } catch (error: unknown) {
        type AxiosLikeError = { response?: { status?: number; data?: unknown } };
        const err = error as AxiosLikeError;
        const status = err.response?.status;
        const data = err.response?.data;
        let detail = '';
        if (typeof data === 'string') {
            detail = data;
        } else if (data && typeof data === 'object') {
            const obj = data as Record<string, unknown>;
            const message = obj['message'];
            const errorText = obj['error'];
            if (typeof message === 'string') detail = message;
            else if (typeof errorText === 'string') detail = errorText;
            else detail = JSON.stringify(obj);
        }
        const prefix = status ? `Order creation failed (HTTP ${status})` : 'Failed to create order';
        const errorMessage = detail ? `${prefix}: ${detail}` : `${prefix}.`;
        throw new Error(errorMessage);
    }
}