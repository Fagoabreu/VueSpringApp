<template>
    <layout-container>
        <div class="container-fluid">
            <h2 class="text-center mt-5 mb-3">Transferências</h2>
            <div class="card">
                <div class="card-header">
                    <router-link to="/transfer/create" class="btn btn-outline-primary">
                        Criar transferência
                    </router-link>
                </div>
                <div class="card-body">
                    <table class="table table-bordered">
                        <thead>
                            <tr>
                                <th>Conta Origem</th>
                                <th>Conta Destino</th>
                                <th>Data Transferencia</th>
                                <th>Valor</th>
                                <th width="250px">Ações</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr v-for=" transfer in transfers" :key="transfer.id">
                                <td>{{ transfer.originAccount }}</td>
                                <td>{{ transfer.destinyAccount }}</td>
                                <td>{{ formatDate(transfer.transferDate) }}</td>
                                <td> {{ formatCurrency(transfer.transferValue) }} </td>
                                <td>
                                    <div class="d-grid gap-2 d-md-block">
                                        <router-link :to="`/transfer/edit/${transfer.id}`"
                                            class="btn btn-outline-info mx-1" enabled="${transfer.isEnabled}">
                                            Edit
                                        </router-link>
                                        <router-link :to="`/transfer/detail/${transfer.id}`"
                                            class="btn btn-outline-success mx-1" enabled="${transfer.isEnabled}">
                                            Detail
                                        </router-link>
                                        <button @click="deleteTransfer(transfer.id)"
                                            class="btn btn-outline-danger mx-1">
                                            Delete
                                        </button>
                                    </div>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </div>
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
    name: 'TransfersOverview',
    components: {
        LayoutContainer,
    },
    data() {
        return {
            transfers: []
        };
    },
    created() {
        this.getAllTransfers();
    },
    methods: {
        getAllTransfers() {
            axios.get(`/v1/transfers`)
                .then(response => {
                    this.transfers = response.data;
                    this.transfers.forEach(transfer => {
                        if (transfer.transferDate > new Date()) {
                            transfer.isEnabled = true;
                        } else {
                            transfer.isEnabled = false;
                        }
                    });
                    return response;
                })
                .catch(error => {
                    return error;
                })
        },
        deleteTransfer(id) {
            Swal.fire({
                icon: 'warning',
                title: 'Confirmar remoção',
                text: 'A transferência será removida permanentemente',
                showCancelButton: true,
                confirmButtonColor: '#3085de',
                cancelButtonColor: '#d33',
                confirmButtonText: 'Remover transferência'
            }).then(result => {
                if (result.isConfirmed) {
                    axios.delete(`/v1/transfers/${id}`)
                        .then(response => {
                            Swal.fire({
                                icon: 'success',
                                title: 'Transferência Removida',
                                showConfirmButton: false,
                                timer: 1500
                            })
                            this.getAllTransfers();
                            return response;
                        })
                }
            }).catch(error => {
                Swal.fire({
                    icon: 'error',
                    title: 'Falha ao remover a transferência',
                    showConfirmButton: false,
                    timer: 1500
                })
                return error;
            })
        },
        formatDate(rawDate) {
            return useDateFormat(rawDate, 'DD/MM/YYYY');
        },
        formatCurrency(rawCurrency) {
            return format(rawCurrency, config);
        }
    }
}
</script>