<template>
  <v-card>
    <v-card-title>
      Consultations
      <v-spacer></v-spacer>
      <v-text-field
        v-model="search"
        append-icon="mdi-magnify"
        label="Search"
        single-line
        hide-details
      ></v-text-field>
      <v-btn @click="addConsultation">Add Consultation</v-btn>
      <v-btn @click="switchView">Back</v-btn>
    </v-card-title>
    <v-data-table
      :headers="headers"
      :items="consultations"
      :search="search"
      @click:row="editConsultation"
    ></v-data-table>
    <ConsultationDialog
      :opened="dialogVisible"
      :consultation="selectedConsultation"
      @refresh="refreshList"
    ></ConsultationDialog>
  </v-card>
</template>

<script>
import ConsultationDialog from "../components/ConsultationDialog";
import api from "../api";
import router from "../router";

export default {
  name: "ConsultationList",
  components: { ConsultationDialog },
  data() {
    return {
      consultations: [],
      search: "",
      headers: [
        {
          text: "Date",
          align: "start",
          value: "date",
        },
        { text: "Hour", value: "time" },
        { text: "Doctor", value: "doctor_id" },
        { text: "Patient", value: "patient_id" },
        { text: "Text", value: "text" },
      ],
      dialogVisible: false,
      selectedConsultation: {},
    };
  },
  methods: {
    editConsultation(consultation) {
      this.selectedConsultation = consultation;
      this.dialogVisible = true;
    },
    addConsultation() {
      this.dialogVisible = true;
    },
    async refreshList() {
      this.dialogVisible = false;
      this.selectedConsultation = {};
      this.consultations = await api.consultations.allConsultations();
    },
    switchView() {
      router.back();
    },
  },
  created() {
    this.refreshList();
  },
};
</script>

<style scoped></style>
