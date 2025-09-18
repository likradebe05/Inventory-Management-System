import axios from 'axios'

export const api = axios.create({
    baseURL: process.env.NEXT_PUBLIC_API_BASE_URL || 'http://localhost:8088',
    timeout: 30000, // 30 second timeout
    headers: {
        'Content-Type': 'application/json',
    },
})

// Authentication disabled for presentation
/*
api.interceptors.request.use((config) => {
    if (typeof window !== 'undefined') {
        const token = localStorage.getItem('token')
        if (token) {
            config.headers.Authorization = `Bearer ${token}`
        }
    }
    return config
})
*/
