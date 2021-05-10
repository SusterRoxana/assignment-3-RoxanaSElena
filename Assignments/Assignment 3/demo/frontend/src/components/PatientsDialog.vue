<template>
  <v-dialog
    transition="dialog-bottom-transition"
    max-width="600"
    :value="opened"
  >
    <template>
      <v-card>
        <v-toolbar color="primary" dark>
          {{ isNew ? "Create Patient" : "Edit Patient" }}
        </v-toolbar>
        <v-form>
          <v-text-field v-model="patient.name" label="Name" />
          <v-text-field v-model="patient.cardNumber" label="Card Number" />
          <v-text-field v-model="patient.cnp" label="CNP" />
          <v-text-field v-model="patient.birthDate" label="Birth Date" />
          <v-text-field v-model="patient.address" label="Address" />
        </v-form>
        <v-card-actions>
          <v-btn @click="persist">
            {{ isNew ? "Create" : "Save" }}
          </v-btn>
          <v-btn @click="deletePatient">Delete</v-btn>
        </v-card-actions>
      </v-card>
    </template>
  </v-dialog>
</template>

<script>
import api from "../api";

export default {
  name: "PatientDialog",
  props: {
    patient: Object,
    opened: Boolean,
  },
  methods: {
    persist() {
      if (this.isNew) {
        api.patients
          .create({
            name: this.patient.name,
            cardNumber: this.patient.cardNumber,
            cnp: this.patient.cnp,
            birthDate: this.patient.birthDate,
            address: this.patient.address,
          })
          .then(() => this.$emit("refresh"));
      } else {
        api.patients
          .edit({
            id: this.patient.id,
            name: this.patient.name,
            cardNumber: this.patient.cardNumber,
            cnp: this.patient.cnp,
            birthDate: this.patient.birthDate,
            address: this.patient.address,
          })
          .then(() => this.$emit("refresh"));
      }
    },
    deletePatient() {
      api.patients.delete(this.patient.id).then(() => this.$emit("refresh"));
    },
  },
  computed: {
    isNew: function () {
      return !this.patient.id;
    },
  },
};
</script>

<style scoped></style>
