<template>
  <v-dialog
    transition="dialog-bottom-transition"
    max-width="600"
    :value="opened"
  >
    <template>
      <v-card>
        <v-toolbar color="primary" dark>
          {{ isNew ? "Create Consultation" : "Edit" }}
        </v-toolbar>
        <v-form>
          <v-text-field v-model="consultation.date" label="Date" />
          <v-text-field v-model="consultation.time" label="Hour" />
          <v-text-field
            v-model="consultation.patient_id"
            label="Patient's id"
          />
          <v-text-field v-model="consultation.doctor_id" label="Doctor's id" />
          <v-text-field v-model="consultation.text" label="Text" />
        </v-form>
        <v-card-actions>
          <v-btn @click="persist">
            {{ isNew ? "Create" : "Save" }}
          </v-btn>
          <v-btn @click="deleteConsultation">Delete</v-btn>
        </v-card-actions>
      </v-card>
    </template>
  </v-dialog>
</template>

<script>
import api from "../api";

export default {
  name: "ConsultationDialog",
  props: {
    consultation: Object,
    opened: Boolean,
  },
  methods: {
    persist() {
      if (this.isNew) {
        api.consultations
          .create({
            date: this.consultation.date,
            time: this.consultation.time,
            patient_id: this.consultation.patient_id,
            doctor_id: this.consultation.doctor_id,
            text: this.consultation.text,
          })
          .then(() => this.$emit("refresh"));
      } else {
        api.consultations
          .edit({
            id: this.consultation.id,
            date: this.consultation.date,
            doctor_id: this.consultation.doctor_id,
            patient_id: this.consultation.patient_id,
            text: this.consultation.text,
          })
          .then(() => this.$emit("refresh"));
      }
    },
    deleteConsultation() {
      api.consultations
        .delete(this.consultation.id)
        .then(() => this.$emit("refresh"));
    },
  },
  computed: {
    isNew: function () {
      return !this.consultation.id;
    },
  },
};
</script>

<style scoped></style>
