import { api } from './axios'

/**
 * Admin login function
 * Note: Admin signup is not available through public API for security
 */
export async function login(payload: { email: string, password: string }) {
    const { data } = await api.post('/auth/login', payload)
    return data as { 
        token: string, 
        user: { 
            email: string,
            role: string,
            roleCode: string,
            authority: string,
            userType: string
        } 
    }
}

// Admin signup removed for security - admins are created through database seeding