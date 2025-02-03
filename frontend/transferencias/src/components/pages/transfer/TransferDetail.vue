<template>
    <layout-container>
        <h2 class="text-center mt-5 mb-3">Detalhe Transferencia</h2>
        <div class="card">
            <div class="card-header">
                <router-link to="/transfer" class="btn btn-outline-info float-right">
                    Transferencias
                </router-link>
            </div>
            <div class="card-body">
                <b class="text-muted"> Conta Origem:</b>
                <p>{{ transfer.originAccount }}</p>
                <b class="text-muted"> Conta Destino:</b>
                <p>{{ transfer.destinyAccount }}</p>
                <b class="text-muted"> Valor Transferência</b>
                <p>{{ formatCurrency(transfer.transferValue) }}</p>
                <b class="text-muted"> Valor Taxa</b>
                <p>{{ formatCurrency(transfer.taxValue) }}</p>
                <b class="text-muted"> Data Transferência</b>
                <p>{{ formatDate(transfer.transferDate, false) }}</p>
                <b class="text-muted"> Criado em</b>
                <p>{{ formatDate(transfer.createdAt, true) }}</p>
                <b class="text-muted"> Ultima alteração</b>
                <p>{{ formatDate(transfer.updatedAt, true) }}</p>
            </div>
        </div>
    </layout-container>
</template>
<script>
import LayoutContainer from '../../LayoutContainer.vue';
import axios from 'axios';
import Swal from 'sweetalert2';
import { useDateFormat } from '@vueuse/core';
import { format } from 'v-money3';

const config = {
    debug: false,
    masked: false,
    prefix: 'R$ ',
    suffix: '',
    thousands: '.',
    decimal: ',',
    precision: 2,
    disableNegative: false,
    disabled: false,
    allowBlank: false,
    minimumNumberOfCharacters: 0,
    modelModifiers: {
        number: false,
    },
    shouldRound: true,
    focusOnRight: false,
}

export default {
    name: 'TranferDetail',
    components: {
        LayoutContainer,
    },
    data() {
        return {
            transfer: {
                originAccount: '',
                destinyAccount: '',
                transferValue: 0,
                taxValue: 0,
                transferDate: '',
                createdAt: '',
                updatedAt: '',
            },
        }
    },
    created() {
        let id = this.$route.params.id;
        this.getTransferDetail(id);
    },
    methods: {
        getTransferDetail(id) {
            axios.get(`v1/transfers/${id}`)
                .then(response => {
                    this.transfer = response.data;
                    return response;
                })
                .catch(error => {
                    Swal.fire({
                        icon: 'error',
                        title: 'Erro ao carregar transferência',
                        showConfirmButton: false,
                        timer: 1500
                    })
                    return error;
                });
        }, formatDate(rawDate, hasTime) {
            if (hasTime && hasTime == true) {
                return useDateFormat(rawDate, 'DD/MM/YYYY HH:mm:ss');
            }
            return useDateFormat(rawDate, 'DD/MM/YYYY');
        }, formatCurrency(rawCurrency) {
            return format(rawCurrency, config);
        }

    }
}
</script>