<template>
    <layout-container>
        <h2 class="text-center mt-5 mb-3">Alterar Transferência</h2>
        <div class="card">
            <div class="card-header">
                <router-link to="/transfer" class="btn btn-outline-info float-right">
                    Transferências
                </router-link>
            </div>
            <div class="card-body">
                <form>
                    <div class="form-group">
                        <label for="transferOriginAccount">Conta Origem</label>
                        <input type="text" v-model="transfer.originAccount" v-maska="'**********'" class="form-control"
                            id="transferOriginAccount" name="transferOriginAccount" />
                    </div>
                    <div class="form-group">
                        <label for="transferDestinyAccount">Conta Destino</label>
                        <input type="text" v-model="transfer.destinyAccount" v-maska="'**********'" class="form-control"
                            id="transferDestinyAccount" name="transferDestinyAccount" />
                    </div>
                    <div class="form-group">
                        <label for="transferTransferDate">Data da transferência</label>
                        <VueDatePicker id="transferTransferDate" name="transferTransferDate" locale="pt-BR"
                            month-name-format="long" v-model="transfer.transferDate" :min-date="new Date()" inline
                            auto-apply :enable-time-picker="false" v-on:blur="calculateTax()">
                        </VueDatePicker>
                    </div>
                    <div class="form-group">
                        <label for="transferValue">Valor Transferencia</label>
                        <money3 v-model.number="transfer.transferValue" class="form-control" id="transferValue"
                            name="transferValue" v-bind="config" v-on:blur="calculateTax()" />
                    </div>
                    <div class=" form-group">
                        <label for="transferTaxValue">Valor Taxa</label>
                        <money3 v-model.number="transfer.taxValue" class="form-control" id="transferTaxValue"
                            name="transferTaxValue" v-bind="config" :readonly="true" />
                    </div>
                    <button @click="updateTransfer(transfer.id)" :disabled="isSubmitting" type="button"
                        class="btn btn-outline-warning mt3">Enviar</button>
                </form>
            </div>
        </div>
    </layout-container>
</template>
<script>
import LayoutContainer from '../../LayoutContainer.vue';
import axios from 'axios';
import Swal from 'sweetalert2';
import VueDatePicker from '@vuepic/vue-datepicker';
import { vMaska } from "maska/vue"
import { Money3Component } from 'v-money3'

export default {
    name: 'UpdateTransfer',
    components: {
        LayoutContainer,
        VueDatePicker,
        money3: Money3Component
    },
    directives: { maska: vMaska },
    data() {
        return {
            transfer: {
                originAccount: '',
                destinyAccount: '',
                transferValue: '',
                taxValue: '',
                transferDate: '',
            },
            rules: [],
            isSubmitting: false,
            taxAplied: false,
            config: {
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
        },
        updateTransfer(id) {
            if (!this.checkForm()) {
                return;
            }
            this.isSubmitting = true;
            axios
                .put(`v1/transfers/${id}`, this.transfer)
                .then(response => {
                    Swal.fire({
                        icon: 'success',
                        title: 'Transferência atualizada',
                        showConfirmButton: false,
                        timer: 1500
                    });
                    this.isSubmitting = false;
                    return response;
                })
                .catch(error => {
                    this.isSubmitting = false;
                    Swal.fire({
                        icon: 'error',
                        title: 'Erro ao atualizar transferência',
                        showConfirmButton: false,
                        timer: 1500
                    })
                    return error;
                });
        }, checkForm() {
            let errors = "";
            if (!this.transfer.originAccount) {
                errors += ('Conta Origem vazio.<br>');
            }
            if (!this.transfer.destinyAccount) {
                errors += ('Conta Destino vazio.<br>');
            }
            if (this.transfer.originAccount && this.transfer.destinyAccount && this.transfer.originAccount == this.transfer.destinyAccount) {
                errors += ('Origem e Destino são iguais.<br>');
            }
            if (!this.transfer.transferDate) {
                errors += ('Data Transferencia vazia.<br>');
            } else if (new Date(this.transfer.transferDate) < new Date) {
                errors += ('Data Transferencia menor que a data atual.<br>');
            }

            if (!this.transfer.transferValue) {
                errors += ('Valor Transferencia vazio.<br>');
            } else if (this.transfer.transferValue < this.transfer.taxValue) {
                errors += ('Valor Transferencia menor que a taxa calculada.<br>');
            }

            if (errors.length === 0) {
                return true;
            }
            Swal.fire({
                icon: 'error',
                title: 'Erro ao criar transferência',
                html: errors,
                showConfirmButton: false,
                timer: 3000
            })
            return false;
        }, calculateTax() {
            this.transfer.taxValue = 0;
            this.taxAplied = false;
            if (!this.transfer.transferDate || !this.transfer.transferValue) {
                return;
            }
            this.isSubmitting = true;
            const days = Math.round((this.transfer.transferDate - new Date()) / 86400000);
            axios
                .get(`/v1/rules?days=${days}`)
                .then(response => {
                    this.rules = response.data;
                    let taxPct = 0;
                    let rate = 0;
                    if (this.rules.lenght > 0) {
                        this.taxAplied = true;
                    }
                    this.rules.forEach(rule => {
                        taxPct += rule.taxPct;
                        rate += rule.rate;
                    });
                    this.transfer.taxValue = rate + this.transfer.transferValue * taxPct / 100;
                    this.isSubmitting = false;
                })
                .catch(error => {
                    Swal.fire({
                        icon: 'error',
                        title: 'Erro ao carregar regras',
                        showConfirmButton: false,
                        timer: 1500
                    })
                    this.isSubmitting = false;
                    return error;
                });
        }
    }
}
</script>